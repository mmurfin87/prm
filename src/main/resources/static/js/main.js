function deleteContact(id) {
    if (confirm('Are you sure you want to delete this contact?')) {
        fetch(`/contacts/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/contacts';
                } else {
                    alert('Error deleting contact');
                }
            });
    }
}