import axios from "axios";
import { useEffect, useState } from "react";

function Product() {
  const [Product, setProduct] = useState([]);
  const [NewProduct, setNewProduct] = useState({ nom: '', price: '' });
  const [updatedProduct, setUpdatedProduct] = useState({ nom: '', price: '' });
  const [selectedProductId, setSelectedProductId] = useState(null); // Pour stocker l'ID du produit à mettre à jour

  // Récupérer les produits depuis le backend
  useEffect(() => {
    axios.get("http://localhost:8081/Product/getall")
      .then((response) => {
        setProduct(response.data);
      })
      .catch((error) => {
        console.error("Il y a une erreur lors de la récupération des produits", error);
      });
  }, []);

  // Gestion de la soumission du formulaire pour ajouter un produit
  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8081/Product/ajouteProduct", NewProduct)
      .then((response) => {
        setProduct([...Product, response.data]);  // Ajoute le produit à la liste
        setNewProduct({ nom: '', price: '' });    // Réinitialise le formulaire
      })
      .catch((error) => {
        console.error("Il y a une erreur lors de l'ajout du produit", error);
      });
  };

  // Gestion des changements dans le formulaire
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewProduct({ ...NewProduct, [name]: value });
  };

  const handleUpdateInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedProduct({ ...updatedProduct, [name]: value });
  };

  // Gestion de la suppression d'un produit
  const handleDelete = (id) => {
    axios.delete(`http://localhost:8081/Product/delete/${id}`)
      .then(() => {
        setProduct(Product.filter((prod) => prod.id !== id));
      })
      .catch((error) => {
        console.error("Erreur lors de la suppression du produit", error);
      });
  };

  // Gestion de la mise à jour d'un produit
  const handleUpdate = (e) => {
    e.preventDefault();
    if (selectedProductId !== null) {
      axios.put(`http://localhost:8081/Product/changeProduct/${selectedProductId}`, updatedProduct)
        .then((response) => {
          setProduct(Product.map((prod) => 
            prod.id === selectedProductId ? response.data : prod
          ));
          setUpdatedProduct({ nom: '', price: '' }); // Réinitialise le formulaire de mise à jour
          setSelectedProductId(null); // Réinitialise l'ID du produit sélectionné
        })
        .catch((error) => {
          console.error("Erreur lors de la mise à jour du produit", error);
        });
    }
  };

  const handleEditClick = (prod) => {
    setSelectedProductId(prod.id);
    setUpdatedProduct({ nom: prod.nom, price: prod.price }); // Prend les valeurs actuelles du produit pour les afficher dans le formulaire
  };

  return (
    <>
      <div>
        <form onSubmit={handleSubmit}>
          <label>Ajouter un nom</label>
          <input type="text" name="nom" onChange={handleInputChange} value={NewProduct.nom} />
          <br />
          <label>Ajouter un prix</label>
          <input type="text" name="price" onChange={handleInputChange} value={NewProduct.price} />
          <input type="submit" value="Ajouter le produit" />
        </form>
      </div>

      <div>
        <h1>Liste des produits</h1>
        <ul>
          {Product.map((prod) => (
            <li key={prod.id}>
              {prod.nom} - {prod.price} 
              <button onClick={() => handleDelete(prod.id)}>Supprimer le produit</button>
              <button onClick={() => handleEditClick(prod)}>Modifier le produit</button>
            </li>
          ))}
        </ul>
      </div>

      {selectedProductId && (
        <div>
          <h2>Mettre à jour le produit</h2>
          <form onSubmit={handleUpdate}>
            <label htmlFor="">Changer nom</label>
            <input type="text" name='nom' onChange={handleUpdateInputChange} value={updatedProduct.nom} />
            <br />
            <label htmlFor="">Changer prix</label>
            <input type="text" name='price' onChange={handleUpdateInputChange} value={updatedProduct.price} />
            <button type="submit">Mettre à jour</button>
          </form>
        </div>
      )}
    </>
  );
}

export default Product;
