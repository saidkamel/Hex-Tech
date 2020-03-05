/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Activite;
import java.util.List;

/**
 *
 * @author sa3do
 */
public interface IActivite {
    public void ajouterActivite(Activite A);
    public List<Activite> getListActivite();
    public void supprimerActivite(int id);
    public void modifierActivite(int id, String nom, String desc, String type);
    public List<Activite> trieActivite(String o);
    public List<Activite> rechercheActivite(String column, String o);
    
}
