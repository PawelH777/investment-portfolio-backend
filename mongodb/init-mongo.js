db.createUser(
	{
		user		: "username",
		password	: "password",
		roles		: [
			{
				role : "readWrite"
				db	 : "investment-portfolio"
			}
		]
	}
);