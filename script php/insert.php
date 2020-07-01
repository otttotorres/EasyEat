<?php
include 'conexion.php'
$user=$_POST['user'];
$pass=$_POST['pass'];

$query="insert into usuario values ('"$user"','"$pass"')"

mysqli_query($conexion,query) or die(mysqli_error());
mysqli_close($conexion);

?> 