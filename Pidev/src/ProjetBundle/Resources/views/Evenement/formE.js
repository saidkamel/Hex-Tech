const nomP = document.getElementById('nomP')
const prenom = document.getElementById('prenom')
const form = document.getElementById('form')

form.addEventListener(('Submit'),(evt => {
    let messages =[]
    if(nomP.value == '' || nomP.value==null){
        messages.push('Name is Required')
    }
    e.preventDefault()
    if ( nomP.value.length <= 6){
        messages.push('le Nom doit etre supérieur a 6')
    }
}))