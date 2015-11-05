

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;



public class WarcReader {

  public static void main(String[] args) throws IOException {
	  brokenlink j = new brokenlink();
    String inputWarcFile="/home/qu/Desktop/00.warc.gz";
    // open our gzip input stream
    GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(inputWarcFile));
    
    // cast to a data input stream
    DataInputStream inStream=new DataInputStream(gzInputStream);
    
    // iterate through our stream
    WarcRecord thisWarcRecord;
    while ((thisWarcRecord=WarcRecord.readNextWarcRecord(inStream))!=null) {
      // see if it's a response record
      if (thisWarcRecord.getHeaderRecordType().equals("response")) {
        // it is - create a WarcHTML record
        WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(thisWarcRecord);
        // get our TREC ID and target URI
        String thisTRECID=htmlRecord.getTargetTrecID();
        String thisTargetURI=htmlRecord.getTargetURI();
        System.out.println( "Test link " + thisTargetURI);
         if (brokenlink.isLive(htmlRecord.getTargetURI())){
        	System.out.println( "FInal : " + thisTargetURI);
        	System.out.println( "FInal : " + htmlRecord.getRawRecord());
        }
        //System.out.println(htmlRecord.getRawRecord());
        // print our data
        
      }
    }
    
    inStream.close();
  }
}