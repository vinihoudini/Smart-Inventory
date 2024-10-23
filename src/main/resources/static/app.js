const apiUrl = 'http://localhost:8080/api'; // Ajuste para o seu URL se necessário



// Funções para logs de inventário
document.getElementById('inventoryLogForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const logItemId = document.getElementById('logItemId').value;
    const logQuantity = document.getElementById('logQuantity').value;
    const logDescription = document.getElementById('logDescription').value;

    const response = await fetch(`${apiUrl}/inventoryLogs`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ itemId: logItemId, quantity: logQuantity, description: logDescription })
    });

    if (response.ok) {
        alert('Log de inventário criado com sucesso!');
        loadInventoryLogs();
    }
});

async function loadInventoryLogs() {
    const response = await fetch(`${apiUrl}/inventoryLogs`);
    const logs = await response.json();
    const inventoryLogList = document.getElementById('inventoryLogList');
    inventoryLogList.innerHTML = '';
    logs.forEach(log => {
        const li = document.createElement('li');
        li.textContent = `${log.id}: Item ID ${log.itemId}, Quantidade: ${log.quantity}, Descrição: ${log.description}`;
        inventoryLogList.appendChild(li);
    });
}

loadInventoryLogs(); // Carregar logs ao iniciar

// Funções para alertas
document.getElementById('alertForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const alertDescription = document.getElementById('alertDescription').value;
    const alertItemId = document.getElementById('alertItemId').value;

    const response = await fetch(`${apiUrl}/alerts`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ description: alertDescription, itemId: alertItemId })
    });

    if (response.ok) {
        alert('Alerta criado com sucesso!');
        loadAlerts();
    }
});

async function loadAlerts() {
    const response = await fetch(`${apiUrl}/alerts`);
    const alerts = await response.json();
    const alertList = document.getElementById('alertList');
    alertList.innerHTML = '';
    alerts.forEach(alert => {
        const li = document.createElement('li');
        li.textContent = `${alert.id}: ${alert.description} (Item ID: ${alert.itemId})`;
        alertList.appendChild(li);
    });
}

loadAlerts(); // Carregar alertas ao iniciar

// Funções para receitas
document.getElementById('recipeForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const recipeName = document.getElementById('recipeName').value;
    const recipeIngredients = document.getElementById('recipeIngredients').value;
    const recipeInstructions = document.getElementById('recipeInstructions').value;

    const response = await fetch(`${apiUrl}/recipes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: recipeName, ingredients: recipeIngredients, instructions: recipeInstructions })
    });

    if (response.ok) {
        alert('Receita criada com sucesso!');
        loadRecipes();
    }
});

async function loadRecipes() {
    const response = await fetch(`${apiUrl}/recipes`);
    const recipes = await response.json();
    const recipeList = document.getElementById('recipeList');
    recipeList.innerHTML = '';
    recipes.forEach(recipe => {
        const li = document.createElement('li');
        li.textContent = `${recipe.id}: ${recipe.name} (Ingredientes: ${recipe.ingredients})`;
        recipeList.appendChild(li);
    });
}

loadRecipes(); // Carregar receitas ao iniciar

// Funções para pedidos
document.getElementById('orderForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const orderItemId = document.getElementById('orderItemId').value;
    const orderQuantity = document.getElementById('orderQuantity').value;

    const response = await fetch(`${apiUrl}/orders`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ itemId: orderItemId, quantity: orderQuantity })
    });

    if (response.ok) {
        alert('Pedido criado com sucesso!');
        loadOrders();
    }
});

async function loadOrders() {
    const response = await fetch(`${apiUrl}/orders`);
    const orders = await response.json();
    const orderList = document.getElementById('orderList');
    orderList.innerHTML = '';
    orders.forEach(order => {
        const li = document.createElement('li');
        li.textContent = `${order.id}: Item ID ${order.itemId}, Quantidade: ${order.quantity}`;
        orderList.appendChild(li);
    });
}

loadOrders(); // Carregar pedidos ao iniciar

// Funções para listas de compras
document.getElementById('shoppingListForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const shoppingListItemId = document.getElementById('shoppingListItemId').value;
    const shoppingListQuantity = document.getElementById('shoppingListQuantity').value;

    const response = await fetch(`${apiUrl}/shoppingLists`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ itemId: shoppingListItemId, quantity: shoppingListQuantity })
    });

    if (response.ok) {
        alert('Lista de compras criada com sucesso!');
        loadShoppingLists();
    }
});

async function loadShoppingLists() {
    const response = await fetch(`${apiUrl}/shoppingLists`);
    const shoppingLists = await response.json();
    const shoppingList = document.getElementById('shoppingList');
    shoppingList.innerHTML = '';
    shoppingLists.forEach(list => {
        const li = document.createElement('li');
        li.textContent = `Item ID: ${list.itemId}, Quantidade: ${list.quantity}`;
        shoppingList.appendChild(li);
    });
}

loadShoppingLists(); // Carregar listas de compras ao iniciar

// Funções para locais de armazenamento
document.getElementById('storageLocationForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const storageLocationName = document.getElementById('storageLocationName').value;

    const response = await fetch(`${apiUrl}/storageLocations`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: storageLocationName })
    });

    if (response.ok) {
        alert('Local de armazenamento criado com sucesso!');
        loadStorageLocations();
    }
});

async function loadStorageLocations() {
    const response = await fetch(`${apiUrl}/storageLocations`);
    const locations = await response.json();
    const storageLocationList = document.getElementById('storageLocationList');
    storageLocationList.innerHTML = '';
    locations.forEach(location => {
        const li = document.createElement('li');
        li.textContent = `${location.id}: ${location.name}`;
        storageLocationList.appendChild(li);
    });
}

loadStorageLocations(); // Carregar locais de armazenamento ao iniciar


// CRIAR USUARIO (FUNCIONA)
document.getElementById('userForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Enviar a requisição POST para criar o usuário
    fetch('/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, email, password }),
})
        .then(response => {
            if (response.ok) {
                showAlert('Usuário criado com sucesso!');
                return response.json();
            }
            throw new Error('Erro ao criar usuário');
        })
        .then(data => {
            console.log(data);
            // Atualizar a lista de usuários, se necessário
        })
        .catch(error => alert(error.message));
});


// Repita para as demais entidades, seguindo o mesmo padrão
// Adicione o mesmo padrão para as outras entidades (categories, items, etc.)

/////////////////////////////////////



// Adicionar lógica para outros formulários e botões
// Adicionar listeners para os botões de atualização e deleção conforme necessário.


/////////////////////////////////////

// Função para mostrar alerta
function showAlert(message) {
    const alertBox = document.getElementById('alertBox');
    alertBox.innerText = message;
    alertBox.style.display = 'block';
    setTimeout(() => alertBox.style.display = 'none', 3000);
}

// Função para mostrar a caixa de confirmação (FUNCIONA)
function showConfirmation(message) {
    const confirmationBox = document.getElementById('confirmationBox');
    confirmationBox.innerText = message;
    confirmationBox.style.display = 'block';
    setTimeout(() => confirmationBox.style.display = 'none', 3000);
}
/////////////////////////


// Função para pesquisar usuário (FUNCIONA)
document.getElementById('searchUserBtn').addEventListener('click', function () {
    const userId = prompt('Digite o ID do usuário:');
    fetch(`/users/${userId}`)
        .then(response => {
            if (!response.ok) throw new Error('Usuário não encontrado');
            return response.json();
        })
        .then(data => {
            showAlert(`Usuário encontrado: ${JSON.stringify(data)}`);
        })
        .catch(error => alert(error.message));
});

// Botões de atualização e deleção de usuários (FUNCIONA)
document.getElementById('updateUserBtn').addEventListener('click', function () {
    const userId = prompt('Digite o ID do usuário que deseja atualizar:');
    const newEmail = prompt('Digite o novo email:');
    fetch(`/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email: newEmail }),
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao atualizar usuário');
            showConfirmation('Usuário atualizado com sucesso!');
        })
        .catch(error => alert(error.message));
});

document.getElementById('deleteUserBtn').addEventListener('click', function () {
    const userId = prompt('Digite o ID do usuário que deseja deletar:');
    fetch(`/users/${userId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao deletar usuário');
            showConfirmation('Usuário deletado com sucesso!');
        })
        .catch(error => alert(error.message));
});
/////////////////////////////////////
//CATEGORIA

// CRIAR CATEGORIA
document.getElementById('categoryForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const name = document.getElementById('name').value;
    const description = document.getElementById('description').value;

    fetch(`${apiUrl}/categories`, { // Certifique-se de incluir apiUrl aqui
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, description }),
    })
        .then(response => {
            if (response.ok) {
                showAlert('Categoria criada com sucesso!');
                return response.json();
            }
            throw new Error('Erro ao criar categoria');
        })
        .then(() => {
            loadCategories(); // Carregar categorias novamente após a criação
        })
        .catch(error => alert(error.message));
});




async function loadCategories() {
    const response = await fetch(`${apiUrl}/categories`);
    const categories = await response.json();
    console.log(categories); // Adicione este console.log
    const categoryList = document.getElementById('categoryList');
    categoryList.innerHTML = '';
    categories.forEach(category => {
        const li = document.createElement('li');
        li.textContent = `${category.id}: ${category.name} - ${category.description}`;
        categoryList.appendChild(li);
    });
}

    loadCategories(); // Carregar categorias ao iniciar





// Buscar categoria por ID
document.getElementById('searchCategoryBtn').addEventListener('click', function () {
    const categoryId = prompt('Digite o ID da categoria:');
    fetch(`/api/categories/${encodeURIComponent(categoryId)}`)
        .then(response => {
            if (!response.ok) throw new Error('Categoria não encontrada');
            return response.json();
        })
        .then(data => {
            showAlert(`Categoria encontrada: ${JSON.stringify(data)}`);
        })
        .catch(error => alert(error.message));
});

// Atualizar categoria por ID
document.getElementById('updateCategoryBtn').addEventListener('click', function () {
    const categoryId = prompt('Digite o ID da categoria que deseja atualizar:');
    const newName = prompt('Digite o novo nome da categoria:');
    const newDescription = prompt('Digite a nova descrição da categoria:');

    fetch(`/api/categories/${encodeURIComponent(categoryId)}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: newName, description: newDescription }),
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao atualizar categoria');
            showConfirmation('Categoria atualizada com sucesso!');
        })
        .catch(error => alert(error.message));
});

// Deletar categoria por ID
document.getElementById('deleteCategoryBtn').addEventListener('click', function () {
    const categoryId = prompt('Digite o ID da categoria que deseja deletar:');

    fetch(`/api/categories/${encodeURIComponent(categoryId)}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao deletar categoria');
            showConfirmation('Categoria deletada com sucesso!');
        })
        .catch(error => alert(error.message));
});
//////////////////////////////////////////////////////////




//ITENS
// Adicionar um item
document.getElementById('addItemForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Evita o envio do formulário

    const itemName = document.getElementById('itemName').value;
    const itemDescription = document.getElementById('itemDescription').value;
    const itemQuantity = document.getElementById('itemQuantity').value;
    const purchaseDate = document.getElementById('purchaseDate').value; // YYYY-MM-DD
    const storageLocation = document.getElementById('storageLocation').value; // ID ou nome do local
    const expirationDate = document.getElementById('expirationDate').value; // YYYY-MM-DD

    fetch('/items', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: itemName,
            description: itemDescription,
            quantity: itemQuantity,
            purchaseDate: purchaseDate,
            storageLocation: storageLocation,
            expirationDate: expirationDate,
        }),
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao adicionar item');
            return response.json();
        })
        .then(data => {
            alert('Item adicionado com sucesso!');
            // Limpar os campos após a adição
            document.getElementById('itemName').value = '';
            document.getElementById('itemDescription').value = '';
            document.getElementById('itemQuantity').value = '';
            document.getElementById('purchaseDate').value = '';
            document.getElementById('storageLocation').value = '';
            document.getElementById('expirationDate').value = '';
        })
        .catch(error => alert(error.message));
});


// Carregar um item
document.getElementById('loadItemBtn').addEventListener('click', function () {
    const itemId = document.getElementById('loadItemId').value;

    fetch(`/items/${itemId}`, { // Supondo que o endpoint para carregar item seja /items/{id}
        method: 'GET',
    })
        .then(response => {
            if (!response.ok) throw new Error('Item não encontrado');
            return response.json();
        })
        .then(data => {
            document.getElementById('itemOutput').innerText = `Item: ${data.name}, Descrição: ${data.description}`;
        })
        .catch(error => alert(error.message));
});

// Atualizar um item
document.getElementById('updateItemBtn').addEventListener('click', function () {
    const itemId = document.getElementById('updateItemId').value;
    const newName = document.getElementById('updateItemName').value;
    const newDescription = document.getElementById('updateItemDescription').value;

    fetch(`/items/${itemId}`, { // Supondo que o endpoint para atualizar item seja /items/{id}
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: newName, description: newDescription }),
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao atualizar item');
            alert('Item atualizado com sucesso!');
        })
        .catch(error => alert(error.message));
});

// Deletar um item
document.getElementById('deleteItemBtn').addEventListener('click', function () {
    const itemId = document.getElementById('deleteItemId').value;

    fetch(`/items/${itemId}`, { // Supondo que o endpoint para deletar item seja /items/{id}
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao deletar item');
            alert('Item deletado com sucesso!');
        })
        .catch(error => alert(error.message));
});






