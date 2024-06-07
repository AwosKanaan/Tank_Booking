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
    function fetchVehicleData() {
        fetch('http://localhost:8080/vehicles/getAll')
            .then(response => response.json())
            .then(data => {
                const portfolioList = document.querySelector('.portfolio-list');

                data.vehicles.forEach(vehicle => {
                    const card = document.createElement('div');
                    card.className = 'card vehicle-card';

                    const img = document.createElement('img');
                    img.className = 'placeholder-image-icon vehicle-image';
                    img.src = "../../../../public/Tank1.jpeg";
                    img.alt = vehicle.model;
                    card.appendChild(img);

                    const content2 = document.createElement('div');
                    content2.className = 'content2 vehicle-content';
                    card.appendChild(content2);

                    const content3 = document.createElement('div');
                    content3.className = 'content3 vehicle-content-details';
                    content2.appendChild(content3);

                    const heading1 = document.createElement('b');
                    heading1.className = 'heading1 vehicle-model';
                    heading1.textContent = vehicle.model;
                    content3.appendChild(heading1);

                    const button = document.createElement('button');
                    button.className = 'stylesecondary-smallfalse1 order-button';
                    button.onclick = function() {
                        localStorage.setItem('selectedVehicle', JSON.stringify(vehicle));
                        window.location.href = 'order-page-desktop.html';
                    };
                    content3.appendChild(button);

                    const button3 = document.createElement('div');
                    button3.className = 'button3 order-button-text';
                    button3.textContent = 'Order this tank';
                    button.appendChild(button3);

                    const column3 = document.createElement('div');
                    column3.className = 'column3 vehicle-description-column';
                    content2.appendChild(column3);

                    const text1 = document.createElement('div');
                    text1.className = 'text1 vehicle-description';
                    text1.textContent = `Model: ${vehicle.model}, Capacity: ${vehicle.capacity}, Phone: ${vehicle.phoneNumber}`; // Vehicle details as the description
                    column3.appendChild(text1);

                    portfolioList.appendChild(card);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    document.querySelectorAll('.order-button').forEach(button => {
        button.addEventListener('click', function() {
            window.location.href = 'order-page-desktop.html';
        });
    });


    fetchVehicleData();
});
