<?php
 $hostname='localhost';
 $database='login';
 $username='root';
 $password='';

 $conexion = new mysqli($hostname,$username,$password,$database);
 if($conexion->connect_errno){
 	echo "lo sentimos mucho la web no esta disponible";
 }

 ?>