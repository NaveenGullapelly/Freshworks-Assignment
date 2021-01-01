import java.time.Instant;
import java.util.HashMap;
import org.json.simple.JSONObject;

class Value
{
   long timeOut;
   JSONObject value;
   
   Value(int timeOut,JSONObject value)
   {
       this.timeOut=timeOut;
       this.value=value;
   }
}

public class FileBasedStore
{
  private static HashMap<String,Value> file;
 
  public static void CreateData(String key,JSONObject val)
  {
     if(file==null)
     {
        file=new HashMap<String,Value>();
     }
     if(file.containsKey(key))
     {
         System.err.println("Error: The key "+key+"is already created");
     }
     else
     {
       if(key.length() > 32)
       {
           System.err.println("Length of the key is greater than 32");
       }
       else
       {
           if((file.size() < (1024*1024*1024)) && (val.size() < 16*1024*1024) && key.length() <=32)
           {
             int timeOut=0;
             Value valWithTimeout = new Value(timeOut,val);
             
             file.put(key,valWithTimeout);
           }
           else
           {
              System.err.println("Size is too big");
           }
           
       }
     }
  }
 
  public static void CreateData(String key,JSONObject val, int timeOut)
  {
    if(file.containsKey(key))
    {
        System.err.println("Error: This key "+key+"is already created");
    }
    else
    {
        if((file.size() < (1024*1024*1024)) && (val.size() < 16*1024*1024) && key.length() <=32)
        {
            timeOut =(int) (Instant.now().getEpochSecond() + timeOut);
            Value valWithTimeout= new Value(timeOut,val);
            file.put(key,valWithTimeout);
        }
        else
        {
            System.err.println("Size is too big");
        }
    }
  }
 
  public static void ReadData(String key)
  {
      if(file.containsKey(key))
      {
          Value value=file.get(key);
          if((value.timeOut > (int) (Instant.now().getEpochSecond())) || (value.timeOut == 0))
          {
              //  System.out.println(value.timeOut+" : "+Instant.now().getEpochSecond());
               System.out.println(key +" : "+value.value);
          }
          else
          {
              System.err.println("Error: Time has expired for key: "+key);
          }
      }
      else
      {
          System.err.println("Error : The key is not found in the DataStore");
      }
  }
 
  public static void DeleteData(String key)
  {
      if(file.containsKey(key))
      {
          Value value=file.get(key);
          if((value.timeOut<Instant.now().getEpochSecond()))
          {
              file.remove(key);
              System.out.println("The data with the key "+key+" has been Deleted");
          }
          else
          {
              System.err.println("Error: Time has expired for key: "+key);
          }
      }
      else
      {
          System.err.println("Error: The entered Key does not Exist");
      }
  }
 
  public static void main(String args[]) throws Exception
  {
      FileBasedStore fs=new FileBasedStore();
      JSONObject json = new JSONObject();
      json.put("Naveen","21");
      JSONObject json1 = new JSONObject();
      json1.put("Nikhil","19");
      fs.CreateData("NaveenRaj",json);
      fs.ReadData("NaveenRaj");
      fs.CreateData("Lokesh", json1,5);
      fs.ReadData("Lokesh");
      Thread.sleep(6000);
      fs.ReadData("Lokesh");
      fs.DeleteData("NaveenRaj");
      fs.DeleteData("NaveenRaj");
  }
 
}