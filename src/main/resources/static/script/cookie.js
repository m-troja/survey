  document.addEventListener('DOMContentLoaded', function() {
    const popup = document.getElementById('cookie-popup');
    const overlay = document.getElementById('cookie-overlay');
    const okBtn = document.getElementById('cookie-ok-button');

    // Check if user has already consented (using localStorage)
    const consentGiven = localStorage.getItem('cookieConsent');

    if (!consentGiven) {
      // Show popup and overlay
      popup.style.display = 'block';
      overlay.style.display = 'block';

      // Disable scrolling while popup is active
      document.body.style.overflow = 'hidden';
    }

    okBtn.addEventListener('click', () => {
      // Hide popup and overlay
      popup.style.display = 'none';
      overlay.style.display = 'none';

      // Enable scrolling again
      document.body.style.overflow = '';

      // Save consent flag
      localStorage.setItem('cookieConsent', 'true');
    });
  });