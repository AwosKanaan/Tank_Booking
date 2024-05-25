document.addEventListener('DOMContentLoaded', function () {
    // Navigation links
    document.querySelector('.link-one4').addEventListener('click', function () {
        window.location.href = 'order.html';
    });

    document.querySelector('.link-two4').addEventListener('click', function () {
        window.location.href = 'about.html';
    });

    document.querySelector('.link-three4').addEventListener('click', function () {
        // Code to handle services dropdown
    });

    // Menu button
    document.querySelector('.styleprimary-smalltrue-dar2').addEventListener('click', function () {
        // Code to handle menu button click
    });

    // Product variant selection
    document.querySelectorAll('.selected-true, .selected-false, .selected-false1').forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            document.querySelectorAll('.selected-true, .selected-false, .selected-false1').forEach(btn => {
                btn.classList.remove('selected-true');
                btn.classList.add('selected-false');
            });
            this.classList.add('selected-true');
            this.classList.remove('selected-false');
        });
    });

    // Add to Cart button
    document.querySelector('.styleprimary-smallfalse-da3').addEventListener('click', function () {
        alert('Added to cart');
    });

    // Accordion functionality
    document.querySelectorAll('.accordion-item5, .accordion-item6, .accordion-item7').forEach(item => {
        item.addEventListener('click', function () {
            const content = this.querySelector('.text14, .text15, .text16');
            if (content) {
                content.style.display = content.style.display === 'block' ? 'none' : 'block';
            }
        });
    });

    // Subscribe form
    document.querySelector('.stylesecondary-smallfalse10').addEventListener('click', function () {
        const emailInput = document.querySelector('.placeholder3').value;
        if (emailInput) {
            alert(`Subscribed with ${emailInput}`);
        } else {
            alert('Please enter a valid email address');
        }
    });
});
