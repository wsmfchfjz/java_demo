$('.registerBtn').click(register);

function register() {
    $.ajax({
        url: '/user/register',
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({id: 1993, name: "注册用户"}),
        success: function (res) {
            alert(res);
        }
    });
}