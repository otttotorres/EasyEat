<?PHP
$hostname="localhost";
$database="easy_eat";
$username="root";
$password="";
$json=array();
		


		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT correo, codigo, mesa, nombre, precio FROM comanda_comida";

		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["id"]='';
			$results["fecha"]='';
			$results["codigo"]='';
			$results["nombre"]='';
			$results["mesa"]='';
			$results["precio"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
?>