
package gui;

/**
 *
 * @author gisele.galera
 */
public class BDDto {
    
    private String user;
    
    private char[] password;
    
    

    public BDDto(String user, char[] password) {
        this.user = user;
        this.password = password;
    }
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    
    
    
}
