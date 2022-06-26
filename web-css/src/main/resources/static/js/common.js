function getJusho(insertId, instanceModal, zipCodeFrontId, zipCodeBackId, jushoKanjiId, jushoKanaId) {
    //モーダル内の住所リスト、エラーメッセージをクリア
    $(insertId).find('.jusholist').empty();
    $(insertId).find('.modal-error-message').empty();

    $.ajax({
        url:'/getJusho',
        type:'GET',
        data: {
            zipCodeFront: $(insertId).find('.zip-code-front-modal').val(),
            zipCodeBack: $(insertId).find('.zip-code-back-modal').val(),
        },
        dataType: 'json',
        timeout: 3000,
        xhrFields: {
            withCredentials: true
        },
    }).done(function(data){
        //住所1件毎に、イベント設定とラジオボタン要素生成
        $(insertId).find('.jusholist').append('<p>検索結果</p><ul class="collection">');
        data["jushoList"].forEach(function(item){
            const zipCode = item['zipCode'];
            const jushoKanji = item['jushoKanji'];
            const jushoKana = item['jushoKana'];
            const radioInput = $('<li class="collection-item"><label><input name="ju" type="radio"/><span>'+zipCode+' '+jushoKanji+'</span></label></li>');
            //イベント設定。住所が選択されたら、選択された住所を画面本体へ反映する。
            $(radioInput).find('input').on('change', function(){
                $(zipCodeFrontId).val(zipCode.substring(0,3));
                $(zipCodeBackId).val(zipCode.substring(3,7));
                $(jushoKanjiId).val(jushoKanji);
                $(jushoKanaId).val(jushoKana);
            });
            $(insertId).find('.jusholist .collection').append(radioInput);
        });
        $(insertId).find('.jusholist').append('</ul>');
        //イベント設定。住所が選択されたら、モーダルをクローズする。※radioの選択が見えるように、遅延してクローズ。
        $(insertId).find('.jusholist input').each(function(index, element){
            $(element).on('change', function(){
                setTimeout(() => {instanceModal.close()}, 200);
            });
        });
    }).fail(function(data){
        if (data['status'] == 400) {
            //単項目チェックエラーのハンドリング
            if (data['responseJSON']['errorMessage']) {
                console.log(data['responseJSON']['errorMessage']); //削除
                $(insertId).find('.modal-error-message').append('<p>'+data['responseJSON']['errorMessage']+'</p>');
            } else if (data['responseJSON']['errors'] && data['responseJSON']['errors'][0]['defaultMessage']) {
                console.log(data['responseJSON']['errors'][0]['field']); //削除
                console.log(data['responseJSON']['errors'][0]['defaultMessage']); //削除
                $(insertId).find('.modal-error-message').append('<p>'+data['responseJSON']['errors'][0]['defaultMessage']+'</p>');
            } else {
                console.log(data);  //削除
                window.location.href = '/';
            }
        }
        if (data['status'] >= 500 || data['status']  == 0) {
            //システムエラー時はトップへ遷移
            window.location.href = '/';
        }
    });
}

$(document).ready(function(){
    $('.jusho-trigger').each(function(index, triggerElement){
        const insertId = '#'+$(triggerElement).data('insertId');
        const optionsModal = {
            onCloseEnd: function(){
                //モーダルクローズ時に、モーダル内の住所リスト、郵便番号、エラーメッセージをクリア。
                $(insertId).find('.jusholist').empty();
                $(insertId).find('.zip-code-front-modal').val('');
                $(insertId).find('.zip-code-back-modal').val('');
                $(insertId).find('.modal-error-message').empty();
            }
        }
        //モーダルインスタンス構築
        const modalElm = $(insertId).find('.modal')[0];
        const instanceModal = M.Modal.init(modalElm, optionsModal);

        //個別画面側のIDを取得
        const zipCodeFrontId = '#'+$(triggerElement).data('zipCodeFrontId');
        const zipCodeBackId = '#'+$(triggerElement).data('zipCodeBackId');
        const jushoKanjiId = '#'+$(triggerElement).data('jushoKanjiId');
        const jushoKanaId = '#'+$(triggerElement).data('jushoKanaId');

        //モーダル起動ボタン
        $(triggerElement).on('click', function(){
            $(insertId).find('.zip-code-front-modal').val($(zipCodeFrontId).val())
            $(insertId).find('.zip-code-back-modal').val($(zipCodeBackId).val())
            instanceModal.open();
        });

        //モーダル内の検索ボタン
        $(insertId).find('.modal-search').on('click', function(){
            getJusho(insertId, instanceModal, zipCodeFrontId, zipCodeBackId, jushoKanjiId, jushoKanaId);
        });
    });
});
