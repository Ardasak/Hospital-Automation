
package hastaneyönetimsistemi;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class baglanti {
    public static final String kullanici_adi = "root";
    public static final String parola = "";
    
    public static final String db_name = "hastane_yonetim";
    
    public static final String host = "localhost";
    
    public static final int port = 3306;
    
    private static Connection con;
    
    private static Statement statement = null;
    
    private static PreparedStatement preparedStatement = null;
    
    public baglanti(){
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hastane_yonetim",kullanici_adi,parola);  
            System.out.println("Bağlantı başarılı...");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver bulunamadı...");
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<hasta> hastalariGetir(){
        try {
            ArrayList<hasta> cikti = new ArrayList<>();
            statement = con.createStatement();
            String sorgu = "SELECT * FROM hastalar ORDER BY derece DESC";
            ResultSet rs = statement.executeQuery(sorgu);
            
            while(rs.next()){
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String tc = rs.getString("tc_no");
                String hastalık = rs.getString("hastalık");
                int derece = rs.getInt("derece");
                cikti.add(new hasta(ad, soyad, tc, hastalık,  derece));
            }
            return cikti;
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void hasta_guncelle(String eski_ad,String ad, String soyad, String tc, String hastalık, int derece){
        try {
            String sorgu = "UPDATE hastalar SET ad = ?, soyad = ?, tc_no = ?, hastalık = ?, derece = ? WHERE ad = ?";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, tc);
            preparedStatement.setString(4, hastalık);
            preparedStatement.setInt(5, derece);
            preparedStatement.setString(6, eski_ad);
            preparedStatement.executeUpdate();
                    
                    
                    } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    public static void hasta_ekle(String ad, String soyad, String tc, String hastalık, int derece){
        try {
            String sorgu = "INSERT INTO hastalar VALUES(?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, tc);
            preparedStatement.setString(4, hastalık);
            preparedStatement.setInt(5, derece);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void hasta_sil(String name){
        try {
            String sorgu = "DELETE FROM hastalar WHERE ad = ?";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, name);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
