$( document ).ready(function() {
tinymce.init({
                
        selector: 'textarea.tinymce',
        plugins: "image media fullscreen wordcount autoresize",
  	menubar: "file edit insert view format",
	media_live_embeds: true,
        toolbar: "'preview| fontselect fontsizeselect | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | image media fullscreen",
    });
    
});
