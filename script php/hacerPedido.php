<?PHP
$hostname="localhost";
$database="easy_eat";
$username="root";
$password="";
$json=array();
	if(isset($_GET["id"]) && isset($_GET["nombre"])  && isset($_GET["mesa"])  && isset($_GET["precio"])){
		$id=$_GET['id'];
		$nombre=$_GET['nombre'];
		$mesa=$_GET['mesa'];
		$precio=$_GET['precio'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="INSERT INTO comanda(id, nombre, mesa, precio) VALUES ('{$id}','{$nombre}','{$mesa}','{$precio}')";
		$resultado=mysqli_query($conexion,$consulta);

			if($resultado){
		   $consulta="SELECT * FROM comanda";
		   $resultado=mysqli_query($conexion,$consulta);

			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["id"]='';
			$results["nombre"]='';
			$results["mesa"]='';
			$results["precio"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{   
		    $results["id"]='';
			$results["nombre"]='';
			$results["mesa"]='';
			$results["precio"]='';
			$json['datos'][]=$results;
			echo json_encode($json);

		}
?>