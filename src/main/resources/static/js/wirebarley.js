var ready = function () {
    if (data != null) {
        var krw = data.usdkrw;
        krw = changeNumberFormat(krw);
        $("#rate").text(krw + ' KRW/USD');
    }
};

/**
 * 환율정보 조회
 */
var getWireApi = function() {
    var data = {};
    var jqxhr = $.post("getWireInfo.do");
    jqxhr.done(function (result, textStatus, jqxhr) {
        if (textStatus === "success" && result.code === 200) {
            data = result.data;
        }
    });
};


/**
 * 수취국가 선택
 */
var countryChange = function() {
    var country = $("#country option:selected").val();
    getWireApi();
    var rate = 0;
    if (country === 'USDKRW') {
        rate = data.usdkrw;
    } else if (country === 'USDJPY') {
        rate = data.usdjpy;
    } else {
        rate = data.usdphp;
    }
    rate = changeNumberFormat(rate);
    $("#rate").text(rate + ' ' + country.substring(3, 6) + '/' + country.substring(0, 3));

};


var validate = function($form) {
    var wireApply = $form.find("#wireApply").val();
    if (wireApply === "" || wireApply === null) {
        alert("송금액이 바르지 않습니다");
        return false;
    }

    if (!$.isNumeric(wireApply)) {
        alert("송금액이 바르지 않습니다");
        return false;
    }

    if (wireApply < 0 || wireApply > 10000) {
        alert("송금액이 바르지 않습니다");
        return false;
    }

    return true;
};


/**
 * 송금액 계산
 * @param e
 * @returns {boolean}
 */
var calculate = function(e) {
    e.preventDefault();
    var $form = $("form[name='frmCal']");
    if (!validate($form)) return false;

    var country = $("#country option:selected").val();
    var wireApply = $form.find("#wireApply").val();
    var result;
    var rate = 0;

    if (country === 'USDKRW') {
        rate = data.usdkrw;
    } else if (country === 'USDJPY') {
        rate = data.usdjpy;
    } else {
        rate = data.usdphp;
    }

    result = changeNumberFormat(rate * wireApply);
    $("#wireTotal").text('수취금액은 ' + result + ' ' + country.substring(3,6) + ' 입니다');

};


/**
 * 소숫점, 콤마추가
 * @param x
 * @returns {string}
 */
var changeNumberFormat = function(x) {
    if (x != null) {
        var num = x.toFixed(2);
        num = num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return num;
    }
};


$(document).ready(ready)
    .on('click', '.btn_cal', calculate)
    .on('change', '#country', countryChange);