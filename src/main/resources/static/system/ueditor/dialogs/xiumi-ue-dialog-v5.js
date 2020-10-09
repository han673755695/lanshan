/**
 * Created by shunchen_yang on 16/10/25.
 */
UE.registerUI('dialog', function (editor, uiName) {
    var btn = new UE.ui.Button({
        name   : 'xiumi-connect',
        title  : '秀米',
        onclick: function () {
            var dialog = new UE.ui.Dialog({
//                iframeUrl: 'http://127.0.0.1:8081/zhgl/js/ueditor/dialogs/xiumi-ue-dialog-v5.html',
                iframeUrl: editor.ui.UEDITOR_HOME_URL+ 'dialogs/xiumi-ue-dialog-v5.html', 
                editor   : editor,
                name     : 'xiumi-connect',
                title    : "图文助手",
                cssRules : "width: " + (window.innerWidth - 60) + "px;" + "height: " + (window.innerHeight - 60) + "px;",
            });
            dialog.render();
            dialog.open();
        }
    });

    return btn;
});