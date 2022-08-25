import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Users {
    String[] username;
    String[] mobileNo;
    String[] password;
    int newUserID=-1;
    
    Users() throws Exception{
        username = new String[100];
        mobileNo = new String[100];
        password = new String[100];

        File file = new File("Users.txt");
        if(file.createNewFile()){
            System.out.println("File created: " + file.getName());
        }

        Scanner fr = new Scanner(file);

        while(fr.hasNextLine()){
            this.newUserID = fr.nextInt();
            this.username[newUserID] = fr.next();
            this.mobileNo[newUserID] = fr.next();
            this.password[newUserID] = fr.next();
        }

        fr.close();

    }

    void createUser(String username, String mobileNo, String password){
        this.newUserID++;
        this.username[newUserID] = username;
        this.mobileNo[newUserID] = mobileNo;
        this.password[newUserID] = password;
    }

    String[] getUserinfo(String username){
        String[] userinfo = new String[4];

        for(int i=0; i<this.username.length; i++){
            if(username.equals(this.username[i])){
                userinfo[0] = i + "";
                userinfo[1] = this.username[i];
                userinfo[2] = this.mobileNo[i];
                userinfo[3] = this.password[i];
                break;
            }
        }

        return userinfo;
    }

    void saveUserinfo() throws Exception{
        File file = new File("Users.txt");
        
        if(file.createNewFile()){
            System.out.println("File created");
        }

        FileWriter fw = new FileWriter(file);
        fw.write(this.toString());
        fw.close();
    }

    void removeUser(int userID){
        String[] username = new String[100];
        String[] mobileNo = new String[100];
        String[] password = new String[100];

        for(int i=0, j=0; i<=this.newUserID; i++){
            if(i != userID){
                username[j] = this.username[i];
                mobileNo[j] = this.mobileNo[i];
                password[j] = this.password[i];
                j++;
            }
        }

        this.newUserID--;

        for(int i=0; i<=newUserID; i++){
            this.username[i] = username[i];
            this.mobileNo[i] = mobileNo[i];
            this.password[i] = password[i];
        }
    }

    @Override
    public String toString(){
        String info = "";

        for(int i=0; i<=this.newUserID; i++){
            info += i + " " + this.username[i] + " " + this.mobileNo[i] + " " + this.password[i];
            
            if(i != this.newUserID){
                info += "\n";
            }
        }

        return info;
    }
}
