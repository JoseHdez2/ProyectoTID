package cj2.hash_prog;

public class HashObjectMaster {
	public static boolean isSingle(HashObjectVarType var){
		if (var == HashObjectVarType.STRING ||
				var == HashObjectVarType.INT ||
				var == HashObjectVarType.FLOAT)
			return true;
		else
			return false;
	}
	
	public static boolean isPlural(HashObjectVarType var){
		if (!isSingle(var))
			return true;
		else
			return false;
	}
	
	public static boolean canBeASet(HashObjectVarType var){
		return isPlural(var);
	}
}
