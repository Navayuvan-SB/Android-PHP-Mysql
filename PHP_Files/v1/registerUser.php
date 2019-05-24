<?php
	
	require_once '../includes/DbOperations.php';

	$response = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST'){

		if (isset($_POST['username']) and isset($_POST['email']) and isset($_POST['password']))
		//if(true)
			{

			$db = new DbOperations();	
			$result = $db->createUser (
				$_POST['username'],
				$_POST['password'],
				$_POST['email']
				);
			if ($result == 1){

				$response['error'] = false;
				$response['message'] = "User registered Successfully!!";
			}elseif( $result == 2){
				$response['error'] = true;
				$response['message'] = "Some error occurs.. Please try again..!!";
			}elseif (($result == 0)) {
				$response['error'] = true;
				$response['message'] = "Account already exists..!";
			}		

		}else{
			$response['error'] = true;
			$response['message'] = "Required fields are missing";
		}

	}else{
		$response['error'] = true;
		$response['message'] = "Invalid Request";

	}

	echo json_encode($response);