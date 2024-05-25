document.addEventListener('DOMContentLoaded', function () {
    const servicesDropdown = document.querySelector('.nav-link-dropdown');
    const chevronIcon = document.querySelector('.chevron-down-icon');
    const servicesLink = document.querySelector('.link-three');

    servicesDropdown.addEventListener('click', () => {
        servicesLink.classList.toggle('open');
        chevronIcon.classList.toggle('rotate');
    });

    // Handle accordion functionality in FAQ section
    const accordionItems = document.querySelectorAll('.accordion-item, .accordion-item1, .accordion-item2, .accordion-item3, .accordion-item4');

    accordionItems.forEach((item) => {
        item.addEventListener('click', () => {
            item.classList.toggle('active');
            const plusIcon = item.querySelector('img');
            plusIcon.classList.toggle('rotate');
            const answer = item.nextElementSibling;
            if (answer.style.maxHeight) {
                answer.style.maxHeight = null;
            } else {
                answer.style.maxHeight = answer.scrollHeight + 'px';
            }
        });
    });

    // Handle subscription form validation
        const subscribeButton = document.querySelector('#login');
    const emailInput = document.querySelector('.typedefault input');

    subscribeButton.addEventListener('click', (event) => {
        event.preventDefault();
        const email = emailInput.value;
        if (validateEmail(email)) {
            alert('Thank you for subscribing!');
            emailInput.value = '';
        } else {
            alert('Please enter a valid email address.');
        }
    });

    function validateEmail(email) {
        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }
});
