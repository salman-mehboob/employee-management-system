contextPath = "/ems";

function showModal(heading, content, showLoader) {
    if(showLoader === undefined)
        showLoader = true;
    $("#modalHeading").html(heading);
    $("#modalContent").html(content);
    if(!showLoader)
        $('#popup_loader').hide();
    $("#myModal").modal("show");
}

function hideModal() {
    $("#myModal").modal("hide");
}

function authorize(employeeLeaveId) {

    var url = contextPath + "/authorizationForm";
    showModal("Leaves Approval ", "");
    var formData = "employeeLeaveId=" + employeeLeaveId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function leaveDetailAuthorizeForm(employeeLeaveId) {

    var url = contextPath + "/leaveDetailAuthorizeForm";
    showModal("Leaves Approval Detail ", "");
    var formData = "employeeLeaveId=" + employeeLeaveId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function authorizeRegistration(userId) {

    var url = contextPath + "/authorizeRegistrationForm";
    showModal("Registration Approval", "");
    var formData = "userId=" + userId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function userSetting(userId) {

    var url = contextPath + "/userSettings";
    showModal("Settings", "");
    var formData = "userId=" + userId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function edit(employeeId) {

    var url = contextPath + "/employee/edit";
    showModal("Update Profile", "");
    var formData = "employeeId=" + employeeId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}
function authorizeBill(employeeBillId) {

    var url = contextPath + "/bill/authorizeBill";
    showModal("Bills Approval", "");
    var formData = "employeeBillId=" + employeeBillId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}
function billDetailAuthorizeForm(employeeBillId) {

    var url = contextPath + "/bill/billDetailAuthorizeForm";
    showModal("Bills Approval Detail", "");
    var formData = "employeeBillId=" + employeeBillId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function uploadPicture(employeeBillId1) {

    var url = contextPath + "/file/billPic";
    showModal("Upload Picture", "");
    var formData = "employeeBillId1=" + employeeBillId1;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}
function showEmployeeDetail(userId) {

    var url = contextPath + "/employee/showEmployeeDetail";
    showModal("Employee Detail", "");
    var formData = "userId=" + userId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}


function depUpdate(userId) {

    var url = contextPath + "/dependants/depUpdate";
    showModal("Dependants", "");
    var formData = "userId=" + userId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function showBillDetail(billID) {

    var url = contextPath + "/bill/showBillDetail";
    showModal("Bill Detail", "");
    var formData = "billID=" + billID;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}


function billUpdate(billID) {

    var url = contextPath + "/bill/billUpdate";
    showModal("Bill Update", "");
    var formData = "billID=" + billID;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function requestListDetail(employeeLeaveId) {

    var url = contextPath + "/leave/requestListDetail";
     showModal("Leave Requests", "");
    var formData = "employeeLeaveId=" + employeeLeaveId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

function checkComment(taskId) {

    var url = contextPath + "/tasks/taskDetail";
    showModal("Comment Box", "");
    var formData = "taskId=" + taskId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}
function deleteDep(depId) {

    var url = contextPath + "/dependants/showModal";
    showModal("Alert", "");
    var formData = "depId=" + depId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}
function taskDone(taskId) {

    var url = contextPath + "/tasks/taskDetail";
    var formData = "taskId=" + taskId;
    $.post(url, formData, function (data) {
        $('.popup_loader').hide();
        $("#modalContent").html(data);
    });
}

