package Main.mall;

public class Password {
    private static final String secretKey = "rand_key";
    private static final int keyLen =  secretKey.length();
    //시크릿키를 이용해 비밀번호를 encrypt 하는 메소드
    public static String encrypt(String rawPW){
        String encPW  = "" ;
        int len = rawPW.length();
        for(int i=0;i<len;i++){
            encPW += (((secretKey.charAt(i % keyLen) ^ rawPW.charAt(i)) | i) * rawPW.charAt(i)) % 0xFF ;
        }
        return encPW;
    }
}
