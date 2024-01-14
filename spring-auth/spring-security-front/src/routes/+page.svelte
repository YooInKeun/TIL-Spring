<script>
    let isLogin = true;

    const openLoginPopup = () => {
        const popup = window.open('http://localhost:8080/oauth2/authorization/google', 'MyLoginPopup', 'width=600,height=400');
        
        const timer = setInterval(function() { 
            if (popup.closed) {
                clearInterval(timer);
                if (isLogin) {
                    window.location.href = '/index';
                }
            }
        }, 1000);
    };
</script>
  
  
  <main>
    <button on:click={openLoginPopup}>로그인</button>
  </main>