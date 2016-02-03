package honor.util;

import java.util.UUID;

public class UUIDUtil {
	
	
	
	public static String getUuid(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","");
	}

}
