<?PHP
$hostname="localhost";
$database="easy_eat";
$username="root";
$password="";
$json=array();
	if(isset($_GET["correo"]) && isset($_GET["password"])){
		$correo=$_GET['correo'];
		$pwd=$_GET['password'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="INSERT INTO usuarios(correo, password) VALUES ('{$correo}','{$pwd}')";
		$resultado=mysqli_query($conexion,$consulta);

			if($resultado){
		   $consulta="SELECT * FROM usuarios  WHERE correo='{$correo}'";
		   $resultado=mysqli_query($conexion,$consulta);

			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["correo"]='';
			$results["password"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{   
		    $results["correo"]='';
			$results["password"]='';
			$json['datos'][]=$results;
			echo json_encode($json);

		}
?>