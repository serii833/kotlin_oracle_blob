import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager


fun main(args: Array<String>) {

    val url = "jdbc:oracle:thin:asuip/asuip@192.168.2.62:1521:orcl"
    val driver = "oracle.jdbc.OracleDriver"

    Class.forName(driver).newInstance()
    val conn = DriverManager.getConnection(url)
    println("connection opened")

    val statement = conn.prepareStatement("select * from TBLSSDPORTALDATA where n_id = 11656")
    val rs = statement.executeQuery()
    var byteArr = ByteArray(0)


    while(rs.next()) {
        val blob = rs.getBlob("blob1a")
        byteArr = blob.getBytes(1, blob.length().toInt())
    }

    conn.close()

    Files.write(Paths.get("/tmp/kotlin_blob"), byteArr)
}


