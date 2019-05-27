<?php
	
	require_once '../includes/DbOperations.php';

	$response = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST'){

		if (isset($_POST['id']) and isset($_POST['password'])){

			$db = new DbOperations();

			if ($db->shopLogin($_POST['id'], $_POST['password'])){

				$user = $db->getUserByShopId($_POST['id']);
				$response['error'] = false;
				$response['ownerName'] = $user['ownerName'];
				$response['contactNumber'] = $user['contactNumber'];
				$response['id'] = $user['id'];
			}
			else{
				$response['error'] = true;
				$response['message'] = "Invalid username or password";
			}
		}
		else{
			$response['error'] = true;
			$response['message'] = "Required fields are missing";
		}
	}

	echo json_encode($response);

