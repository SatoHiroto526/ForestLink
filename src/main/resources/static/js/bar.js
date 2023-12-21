function toggleSidebar() {
	var sidebar = document.getElementById("sidebar");
    var toggleButton = document.getElementById("toggle-button");
    var closeButton = document.getElementById("close-button");
    var menu = document.querySelector(".menu");
    var content = document.querySelector(".content");

    if (sidebar.classList.contains("open")) {
    	sidebar.classList.remove("open");
    	sidebar.classList.add("closed");
    	toggleButton.style.display = "block"; // 3本線ボタン表示
    	closeButton.style.display = "none"; // ×ボタン非表示
    	menu.style.display = "none"; // メニュー非表示
    	content.style.marginLeft = "100px"; // サイドバーを閉じたときのマージン
    } else {
    	sidebar.classList.remove("closed");
    	sidebar.classList.add("open");
    	toggleButton.style.display = "none"; // 3本線ボタン非表示
    	closeButton.style.display = "block"; // ×ボタン表示
   		menu.style.display = "block"; // メニュー表示
    	content.style.marginLeft = "250px"; // サイドバーを開いたときのマージン
    }
}