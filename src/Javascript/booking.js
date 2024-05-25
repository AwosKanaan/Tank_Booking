document.addEventListener('DOMContentLoaded', function() {
    const navLinkDropdown = document.querySelector('.nav-link-dropdown');
    const chevronIcon = document.querySelector('.chevron-down-icon');

    if (navLinkDropdown) {
        navLinkDropdown.addEventListener('click', function() {
            this.classList.toggle('open');
            if (chevronIcon) {
                chevronIcon.classList.toggle('rotate');
            }
        });
    }

    const placeholders = document.querySelectorAll('.typedefault .placeholder, .typedefault1 .placeholder1, .typedefault2 .placeholder2');
    placeholders.forEach(placeholder => {
        const parent = placeholder.parentElement;

        placeholder.addEventListener('focus', function() {
            parent.classList.add('active');
        });

        placeholder.addEventListener('blur', function() {
            if (this.textContent.trim() === '') {
                parent.classList.remove('active');
            }
        });
    });

    const sendButton = document.querySelector('.styleprimary-smallfalse-da .button2');
    if (sendButton) {
        sendButton.addEventListener('click', function() {
            const name = document.querySelector('.input .name input').value;
            const email = document.querySelector('.input1 .email input').value;
            const message = document.querySelector('.input2 .message textarea').value;
            const agreeTerms = document.querySelector('.selectedfalse .checkbox input').checked;

            if (name && email && message && agreeTerms) {
                alert('Message sent successfully!');
            } else {
                alert('Please fill out all fields and agree to the terms.');
            }
        });
    }

    const subscribeButton = document.querySelector('.stylesecondary-smallfalse1 .button3');
    if (subscribeButton) {
        subscribeButton.addEventListener('click', function() {
            const email = document.querySelector('.typedefault2 .placeholder2 input').value;
            if (email) {
                alert('Subscribed successfully!');
            } else {
                alert('Please enter your email address.');
            }
        });
    }
});
