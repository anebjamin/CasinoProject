
package casino;


public class userBuild {
    private String userID;
    private String userName;
    private String userPW;
    private String userBal;

    public userBuild(String userID, String userName, String userPW, String userBal) {
        this.userID = userID;
        this.userName = userName;
        this.userPW = userPW;
        this.userBal = userBal;
    }
    
    @Override
    public String toString(){
        return userID+ " "+userName+" "+userPW+" "+userBal;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPW() {
        return userPW;
    }

    public String getUserBal() {
        return userBal;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public void setUserBal(String userBal) {
        this.userBal = userBal;
    }


    
    

    
    
}
