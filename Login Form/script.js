
function toggleForms() {
    var loginContainer = document.querySelector('.login-container');
    var registerContainer = document.querySelector('.register-container');
    loginContainer.classList.toggle('hidden');
    registerContainer.classList.toggle('hidden');
}

document.getElementById('show-register').addEventListener('click', function(event) {
    event.preventDefault(); 
    toggleForms();
});

document.getElementById('show-login').addEventListener('click', function(event) {
    event.preventDefault(); 
    toggleForms();
});

document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault(); 
    
   
    var username = document.getElementById('login-username').value;
    var password = document.getElementById('login-password').value;

   
    if (username === localStorage.getItem('username') && password === localStorage.getItem('password')) {
        showNotification('Login successful!');
      
        document.querySelector('.actions').classList.remove('hidden');
        document.querySelector('.borrowed-books').classList.remove('hidden');
        document.querySelector('.book-list').classList.remove('hidden'); // Show book list
        
        document.querySelector('.login-container').classList.add('hidden');
    } else {
        showError('Incorrect username or password. Please try again.');
    }

 
    document.getElementById('login-username').value = '';
    document.getElementById('login-password').value = '';
});

document.getElementById('register-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
   
    var username = document.getElementById('register-username').value;
    var password = document.getElementById('register-password').value;

  
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);

  
    showNotification('Registration successful!');
    
   
    document.querySelector('.login-container').classList.remove('hidden');
    document.querySelector('.register-container').classList.add('hidden');
    
  
    document.getElementById('register-username').value = '';
    document.getElementById('register-password').value = '';
});

document.getElementById('borrow-book').addEventListener('click', function() {
   
    console.log('Borrowing a book...');
});

document.getElementById('return-book').addEventListener('click', function() {
  
    console.log('Returning a book...');
});

document.getElementById('logout').addEventListener('click', function() {
 
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    
  
    document.querySelector('.actions').classList.add('hidden');
    document.querySelector('.borrowed-books').classList.add('hidden');
    document.querySelector('.book-list').classList.add('hidden'); // Hide book list
  
    document.querySelector('.login-container').classList.remove('hidden');
});


var availableBooks = [
    { title: 'Book 1', author: 'Author 1' },
    { title: 'Book 2', author: 'Author 2' },
    { title: 'Book 3', author: 'Author 3' },
 
];


function renderBookList(books) {
    var bookList = document.getElementById('book-list');
    bookList.innerHTML = '';
    
    books.forEach(function(book) {
        var listItem = document.createElement('li');
        listItem.textContent = `${book.title} by ${book.author}`;
        bookList.appendChild(listItem);
    });
}


function filterBooks(searchTerm) {
    var filteredBooks = availableBooks.filter(function(book) {
        return book.title.toLowerCase().includes(searchTerm.toLowerCase()) || book.author.toLowerCase().includes(searchTerm.toLowerCase());
    });
    renderBookList(filteredBooks);
}


renderBookList(availableBooks);


document.getElementById('search-input').addEventListener('input', function(event) {
    var searchTerm = event.target.value.trim();
    filterBooks(searchTerm);
});


function showNotification(message) {
    var notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    document.body.appendChild(notification);
    setTimeout(function() {
        notification.style.opacity = '0';
        setTimeout(function() {
            document.body.removeChild(notification);
        }, 1000);
    }, 3000);
}


function showError(message) {
    var error = document.createElement('div');
    error.className = 'error';
    error.textContent = message;
    document.body.appendChild(error);
    setTimeout(function() {
        error.style.opacity = '0';
        setTimeout(function() {
            document.body.removeChild(error);
        }, 1000);
    }, 3000);
}

