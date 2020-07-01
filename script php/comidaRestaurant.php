<?PHP
$hostname="localhost";
$database="easy_eat";
$username="root";
$password="";
$json=array();
	if(isset($_GET["codigo"])){
		$codigo=$_GET['codigo'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT id, nombre, precio FROM comidas WHERE codigo= '{$codigo}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			while($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["id"]='';
			$results["nombre"]='';
			$results["precio"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{
		   	$results["id"]='';
			$results["nombre"]='';
			$results["precio"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>