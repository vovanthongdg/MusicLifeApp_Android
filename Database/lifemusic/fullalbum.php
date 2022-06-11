<?php  
	require "connect.php";

	$query = "SELECT * FROM album";
	$dataalbum = mysqli_query($conn,$query);

	/**
	 * 
	 */
	class Album
	{
		function Album($idalbum,$tenalbum,$tencasialbum,$hinhalbum)
		{
			$this->IdAlbum = $idalbum;
			$this->TenAlbum = $tenalbum;
			$this->TenCaSiAlbum = $tencasialbum;
			$this->HinhAlbum = $hinhalbum;
		}
	}
	$arrayalbum = array();
	while ($row = mysqli_fetch_assoc($dataalbum)) {
		array_push($arrayalbum, new Album($row['IdAlbum'],
			$row['TenAlbum'],
			$row['TenCaSiAlbum'],
			$row['HinhAlbum']));
	}
	echo json_encode($arrayalbum);


?>