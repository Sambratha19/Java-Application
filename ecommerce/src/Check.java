public class Check {
    public boolean emailCheck(String email){
        for(int i=0;i<email.length();i++){
            if(email.charAt(i)>='A' && email.charAt(i)<='Z'){
                return false;
            }
            String sub=email.substring(i, email.length());
            if(sub.equals("@gmail.com")){
                return true;
            }
        }
        return false;
    }

    public boolean pass(String pass){
        if(pass.length()<5) return false;
        boolean cap=false, num=false;
        for(int i=0;i<pass.length();i++){
            if(pass.charAt(i)>='A'&&pass.charAt(i)<='Z'){
                cap=true;
            }else if(pass.charAt(i)>='0'&&pass.charAt(i)<='9'){
                num=true;
            }
        }

        return (cap&&num)? true:false;
    }
}
