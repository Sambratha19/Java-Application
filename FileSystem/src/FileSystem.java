import java.util.*;
public class FileSystem {
    private Directory root;
    private Stack<DeleteNode> deletedNode;

    FileSystem(){
        this.deletedNode=new Stack<>();
        this.root=new Directory("", null);
    }

    public FileSystemNode travers(String path){
        if(path.equals("/")||path.isEmpty()){return root;}

        String[] parts=path.split("/");
        FileSystemNode current=root;
        for(String part: parts){
            if(part.isEmpty()){
                continue;
            }
            if(!(current instanceof Directory)){
                return null;
            }

            current=((Directory) current).children.get(part);

            if(current==null) return null;


        }
        return current;
    }

    public boolean create(String path, boolean isDir){
        int lastslash=path.lastIndexOf("/");
        String dirPath=lastslash==0 ? "/": path.substring(0, lastslash);
        String name=path.substring(lastslash+1);

        FileSystemNode parentNode=travers(dirPath);
        if(parentNode==null || !parentNode.isDirectory()) return false;

        Directory parentDir=(Directory) parentNode;
        if(parentDir.children.containsKey(name)){
            return false;
        }

        if(isDir){
            Directory newDir=new Directory(name, parentDir);
            parentDir.children.put(name, newDir);
        }else{
            File newFile=new File(name, parentDir);
            parentDir.children.put(name, newFile);
        }
        return true;
    }

    public List<String> list(String path){
        FileSystemNode node=travers(path);
        if(node==null) return Collections.emptyList();
        if(!node.isDirectory())return Arrays.asList(node.name);


        Directory dirNode=(Directory) node;
        List<String> res=new ArrayList<>(dirNode.children.keySet());

        Collections.sort(res);

        return res;

    }

    public boolean UpdateFileContent(String filePath, String newContent){
        FileSystemNode node=travers(filePath);
        if(node==null){
            return false;
        }

        File file=(File) node;

        file.content=new StringBuilder(newContent);

        return true;
    }


    public boolean rename(String path, String newName){
        int lastslash=path.lastIndexOf("/");
        String dirPath=lastslash==0 ? "/": path.substring(0, lastslash);
        String name=path.substring(lastslash+1);

        FileSystemNode parentNode=travers(dirPath);
        if(parentNode==null || !parentNode.isDirectory()) return false;

        Directory parentDir=(Directory) parentNode;
        if(!parentDir.children.containsKey(name)|| parentDir.children.containsKey(newName)) return false;

        FileSystemNode node=parentDir.children.remove(name);
        node.name=newName;
        parentDir.children.put(newName, node);

        return true;

    }

    public boolean delete(String path){
        int lastslash=path.lastIndexOf("/");
        String dirPath=lastslash==0 ? "/": path.substring(0, lastslash);
        String name=path.substring(lastslash+1);

        FileSystemNode parentNode=travers(dirPath);
        if(parentNode==null || !parentNode.isDirectory()) return false;
        Directory parentDir=(Directory) parentNode;
//        if(!parentDir.children.containsKey(name)){return false;}

        FileSystemNode node=parentDir.children.remove(name);
        if(node==null) return false;

        deletedNode.push(new DeleteNode(node, parentDir));
        return true;

    }

    public boolean restore(){
        if(deletedNode.isEmpty()) return false;

        DeleteNode deleteNode=deletedNode.pop();

        Directory parentdir=deleteNode.parent;

        if(parentdir.children.containsKey(deleteNode.node.name)){
            return false;
        }

        parentdir.children.put(deleteNode.node.name, deleteNode.node);
        return true;
    }
}
