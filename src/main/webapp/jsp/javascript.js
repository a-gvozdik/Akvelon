function isValidDate(){
	
	var str = document.newuserform.birthday.value

if(str=="" || str==null){
	document.newuserform.birthday.style.background="#FD8FA7";
	document.newuserform.submit.disabled=true;
	}
if (str.match(/(\d{4})-(\d{2})-(\d{2}$)/)) {
	document.newuserform.birthday.style.background="#FFFFFF";
	document.newuserform.submit.disabled=false;
	} else {
		document.newuserform.birthday.style.background="#FD8FA7";
	document.newuserform.submit.disabled=true;
		}
FieldsValid();
}

function FieldsValid() {
	var fname = document.newuserform.fname.value
	var lname = document.newuserform.lname.value
	var login = document.newuserform.login.value
	var bal = document.newuserform.balance.value
	var birth = document.newuserform.birthday.value
	
	if (fname=="" || lname=="" || login=="" || bal=="" || birth=="") {
		document.newuserform.submit.disabled=true;	
	} else {
		document.newuserform.submit.disabled=false;
	}

}