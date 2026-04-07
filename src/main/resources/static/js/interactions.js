function addToCart(productId) {
    const formData = new URLSearchParams();
    formData.append('productId', productId);

    fetch('/cart/add-ajax', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: formData
    })
    .then(response => response.text())
    .then(cartCount => {
        document.querySelector('.cart-count').innerText = cartCount;
        showToast('Successfully added to your cart!');
    })
    .catch(error => {
        console.error('Error:', error);
        showToast('Failed to add item to cart.', true);
    });
}

function showToast(message, isError = false) {
    const container = document.getElementById('toast-container');
    if (!container) return;

    const toast = document.createElement('div');
    toast.className = 'toast';
    if(isError) toast.style.borderLeft = '4px solid #ef4444';
    
    toast.innerHTML = `
        <div class="toast-content">
            <i class="fas ${isError ? 'fa-exclamation-circle' : 'fa-check-circle'}" style="color: ${isError ? '#ef4444' : '#10b981'};"></i>
            <span>${message}</span>
        </div>
    `;

    container.appendChild(toast);

    // Trigger reflow for animation
    setTimeout(() => {
        toast.classList.add('show');
    }, 10);

    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}
