# Projet n°3 : Entrevoisins

## Présentation
- Entrevoisins est une application utilisée dans le cadre d'une formation [OpenClassrooms](https://openclassrooms.com/fr/paths/511-developpeur-dapplication-android),
- L'objectif de ce projet est d'ajouter des fonctionnalités à une application existante,
- Une fois le projet terminé et les livrables fournis, l'étudiant doit passer une soutenance,
- Si l'examinateur valide l'entretien et le projet, l'étudiant peut passer au projet suivant.

## Installation
- Pour utiliser cette application, téléchargez le logiciel [GitHub Desktop](https://desktop.github.com/),
- Cliquez ensuite sur "Code > Open with GitHub Desktop" depuis la page Github du projet pour télécharger le code,
- Installez ensuite l'EDI [Android Studio](https://developer.android.com/studio) et ouvrez le projet avec,
- Dans Android Studio, vous trouverez `AVD Manager`, qui permet de créer un périphérique virtuel d'émulation,
- Une fois votre périphérique créé, vous pouvez lancer l'application dedans.

## Utilisation

Entrevoisins est un carnet de contacts.

Elle possède trois activités :
- L'affichage des listes de "voisins" normaux et de "voisins" favoris via des onglets,
- L'affichage des informations détaillées d'un "voisin" au clic sur ce dernier depuis les listes,
- La création d'un nouveau "voisin" avec un formulaire pour entrer ses informations détaillées.

En l'utilisant, vous pourrez utiliser les fonctionnalités suivantes :
- Créer un "voisin"
- Marquer un "voisin" comme favori
- Retirer un "voisin" des favoris
- Supprimer un "voisin"
- Lister les "voisins" normaux
- Lister les "voisins" favoris
- Afficher les informations détaillées d'un "voisin"

## Dépendances

Pour fonctionner correctement, cette application utilise les librairies suivantes :

- [EventBus](https://github.com/greenrobot/EventBus) - Pour effectuer des actions lors du déclenchement d'une condition, via un système d'abonnement.
- [Glide](https://github.com/bumptech/glide) - Pour manipuler des images plus rapidement et facilement.
- [Butterknife](https://github.com/JakeWharton/butterknife) - Pour utiliser des éléments de layout avec moins de contraintes.
- [Hamcrest](http://hamcrest.org/JavaHamcrest/distributables) - Pour faciliter l'utilisation d'intents dans les tests.
- [Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core/) - Pour simplifier les tests en retournant directement le résultat d'une fonction plutôt que d'appeler cette dernière.
- [Espresso](https://developer.android.com/training/testing/espresso/setup) - Pour les tests, tout simplement.

**RAPPEL :** Une partie importante du code de l'application existait déjà au début du projet.
 
![Preview1](https://i.imgur.com/onwKwY0.jpg)
![Preview2](https://i.imgur.com/ydVHViR.jpg)
![Preview3](https://i.imgur.com/slCAv9A.jpg)
