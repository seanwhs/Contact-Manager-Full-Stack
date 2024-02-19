//Constant.java
package sean.contact_mgr.constant;

public class Constant {
    //access user (whoever is logged on") home directory
    public static final String PHOTO_DIRECTORY 
     = System.getProperty("user.home") + "/Downloads/uploads/";
    //On windows it maps to C:\Users\{your_username} 
    //and appends /Downloads/uploads/
}
