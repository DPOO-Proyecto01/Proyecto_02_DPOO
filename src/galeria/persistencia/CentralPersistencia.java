package galeria.persistencia;

public class CentralPersistencia 
{
	//Los métodos inician y retornan las clases que serán usadas para la persistencia
	public static PersistenciaInventario getPersistenciaInventario()
	{
		return new PersistenciaInventario();
	}
	
	public static PersistenciaProcesos getPersistenciaProcesos()
	{
		return new PersistenciaProcesos();
	}
	
	public static PersistenciaUsuarios getPersistenciaUsuarios()
	{
		return new PersistenciaUsuarios();
	}
}
