<?php
	
	require_once '../includes/DbOperations.php';

	$response = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST'){

		if (isset($_POST['id'])){

			$db = new DbOperations();

			if ($db->openShop($_POST['id'])){

				$response['error'] = false;
				$response['message'] = "Shop State Changed as OPEN";
			}
			else{
				$response['error'] = true;
				$response['message'] = "Invalid Id";
			}

		}
		else{
				$response['error'] = true;
				$response['message'] = "Required fields are missing";
		}

	}

	echo json_encode($response);