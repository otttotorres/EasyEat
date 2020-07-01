<?PHP
$hostname="localhost";
$database="easy_eat";
$username="root";
$password="";
$json=array();
	if(isset($_GET["user"]) && isset($_GET["pwd"]) && isset($_GET["codigo"])){	
		$correo=$_GET['user'];
		$pwd=$_GET['pwd'];
                $codigo=$_GET['codigo'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT user, pwd, codigo FROM administradores WHERE user= '{$correo}' AND pwd = '{$pwd}' AND codigo='{$codigo}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["user"]='';
			$results["pwd"]='';
                        $results["codigo"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{		
			$results["codigo"]='';
		   	$results["user"]='';
			$results["pwd"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>