/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import model.Jugada;
import model.Resumen;
import model.ResumenAgrupado;
import utils.Config;
///////////////////////////////////////
import static utils.Config.*;

/**
 *
 * @author holasur
 */
public class ResumenAgrupadoController {

    public ArrayList<ResumenAgrupado> listResumenAgrupados(ObservableList<Resumen> resumens) {

        ArrayList<ResumenAgrupado> r = this.generarResumens(resumens);
        ArrayList<ResumenAgrupado> ra = this.agruparResumens(r);
        return ra;
    }

    public ArrayList<ResumenAgrupado> agruparResumens(ArrayList<ResumenAgrupado> resumens) {

        ArrayList<ResumenAgrupado> ri = new ArrayList();

        for (int i = 0; i < resumens.size(); i++) {
            ResumenAgrupado r = resumens.get(i);

            for (int j = i + 1; j < resumens.size(); j++) {
                if (r.equals(resumens.get(j))) {
                    r.sumarUno();
                    r.sumarTotal(resumens.get(j));
                    resumens.remove(j);
                }
            }
            ri.add(r);
        }
        return ri;
    }

    public ArrayList<ResumenAgrupado> generarResumens(ObservableList<Resumen> resumens) {

        ArrayList<ResumenAgrupado> resumensAgrupados = new ArrayList();
        ArrayList<Resumen> primera = new ArrayList();
        ArrayList<Resumen> segunda = new ArrayList();
        ArrayList<Resumen> tercera = new ArrayList();
        ArrayList<Resumen> cuarta = new ArrayList();
        ArrayList<ResumenAgrupado> resumenes = new ArrayList();

        for (Resumen r : resumens) {
            ArrayList<String> list = this.limpiarFormato(r.getTurno());

            int id = r.getId();
            String numero = r.getNumero();
            String quiniela = r.getQuiniela();
            double monto = r.getMonto();
            Date fecha = r.getFecha();
            boolean gano = r.isGano();
            String posiciones = r.getPosiciones();
            String nombre = r.getNombre();
            String idsJugadas = r.getIdJugadas();
            int tipo = r.getTipo();

            if (list.contains(Config.primera)) {

                String turno = Config.primera;
                Resumen r2 = new Resumen(id, numero, turno, quiniela, monto, fecha, gano, posiciones, nombre, idsJugadas, tipo);
                primera.add(r2);
            }
            if (list.contains(Config.segunda)) {

                String turno = Config.segunda;
                Resumen r2 = new Resumen(id, numero, turno, quiniela, monto, fecha, gano, posiciones, nombre, idsJugadas, tipo);
                segunda.add(r2);
            }
            if (list.contains(Config.tercera)) {

                String turno = Config.tercera;
                Resumen r2 = new Resumen(id, numero, turno, quiniela, monto, fecha, gano, posiciones, nombre, idsJugadas, tipo);
                tercera.add(r2);
            }
            if (list.contains(Config.cuarta)) {

                String turno = Config.cuarta;
                Resumen r2 = new Resumen(id, numero, turno, quiniela, monto, fecha, gano, posiciones, nombre, idsJugadas, tipo);
                cuarta.add(r2);
            }

        }
        if (!primera.isEmpty()) {

            ArrayList<ResumenAgrupado> resumenes11 = this.generarResumensAgrupados(primera);
            if (!resumenes11.isEmpty()) {
                ArrayList<ResumenAgrupado> resumenes11Agrupado = this.agruparResumens(resumenes11);
                resumenes.addAll(resumenes11Agrupado);
                resumenes11.stream().forEach((n) -> System.out.println("resumens11:" + n.getNumero() + " " + n.getQuinielas() + " " + n.getCantidad() + " " + n.getTotal()));
            }
        }
        if (!segunda.isEmpty()) {
            ArrayList<ResumenAgrupado> resumenes14 = new ArrayList<>();
            resumenes14 = this.generarResumensAgrupados(segunda);
            if (!resumenes14.isEmpty()) {
                ArrayList<ResumenAgrupado> resumenes14Agrupado = this.agruparResumens(resumenes14);
                resumenes.addAll(resumenes14Agrupado);
                resumenes14.stream().forEach((n) -> System.out.println("resumens14:" + n.getNumero() + " " + n.getQuinielas() + " " + n.getCantidad() + " " + n.getTotal()));
            }
        }
        if (!tercera.isEmpty()) {
            ArrayList<ResumenAgrupado> resumenes17 = new ArrayList<>();
            resumenes17 = this.generarResumensAgrupados(tercera);
            if (!resumenes17.isEmpty()) {
                ArrayList<ResumenAgrupado> resumenes17Agrupado = this.agruparResumens(resumenes17);
                resumenes.addAll(resumenes17Agrupado);
                resumenes17.stream().forEach((n) -> System.out.println("resumens17:" + n.getNumero() + " " + n.getQuinielas() + " " + n.getCantidad() + " " + n.getTotal()));
            }
        }
        if (!cuarta.isEmpty()) {
            ArrayList<ResumenAgrupado> resumenes21 = new ArrayList<>();
            resumenes21 = this.generarResumensAgrupados(cuarta);
            if (!resumenes21.isEmpty()) {
                ArrayList<ResumenAgrupado> resumenes21Agrupado = this.agruparResumens(resumenes21);
                resumenes.addAll(resumenes21Agrupado);
                resumenes21.stream().forEach((n) -> System.out.println("resumens21:" + n.getNumero() + " " + n.getQuinielas() + " " + n.getCantidad() + " " + n.getTotal()));
            }
        }
        return resumenes;
    }

    public ArrayList<ResumenAgrupado> generarResumensAgrupados(ArrayList<Resumen> resumens) {
        if (!resumens.isEmpty()) {
            ArrayList<ResumenAgrupado> seis = new ArrayList();
            ArrayList<ResumenAgrupado> cinco = new ArrayList();
            ArrayList<ResumenAgrupado> cuatro = new ArrayList();
            ArrayList<ResumenAgrupado> tres = new ArrayList();
            ArrayList<ResumenAgrupado> dos = new ArrayList();
            ArrayList<ResumenAgrupado> una = new ArrayList();
            for (Resumen r : resumens) {
                ArrayList<String> list = this.limpiarFormato(r.getQuiniela());

                if (list.size() == 6) {
                    ResumenAgrupado ra = this.genraraResumenAgrupado(r, r.getQuiniela());
                    seis.add(ra);
                } else if (list.size() == 5) {
                    if (!list.contains("E")) {
                        ResumenAgrupado ra = this.genraraResumenAgrupado(r, r.getQuiniela());
                        cinco.add(ra);
                    } else if (!list.contains("C")) {
                        ResumenAgrupado ra4 = this.genraraResumenAgrupado(r, "N P O F");
                        cuatro.add(ra4);
                        ResumenAgrupado ra1 = this.genraraResumenAgrupado(r, "E");
                        una.add(ra1);
                    } else if (!list.contains("F")) {
                        ResumenAgrupado ra3 = this.genraraResumenAgrupado(r, "N P O");
                        tres.add(ra3);
                        ResumenAgrupado ra1 = this.genraraResumenAgrupado(r, "E");
                        una.add(ra1);
                        ResumenAgrupado ra1b = this.genraraResumenAgrupado(r, "C");
                        una.add(ra1b);
                    } else if (!list.contains("O")) {
                        ResumenAgrupado ra2 = this.genraraResumenAgrupado(r, "N P");
                        dos.add(ra2);
                        ResumenAgrupado ra1 = this.genraraResumenAgrupado(r, "E");
                        una.add(ra1);
                        ResumenAgrupado ra1b = this.genraraResumenAgrupado(r, "C");
                        una.add(ra1b);
                        ResumenAgrupado ra1c = this.genraraResumenAgrupado(r, "F");
                        una.add(ra1c);
                    } else {
                        for (String ra : list) {
                            ResumenAgrupado rax = this.genraraResumenAgrupado(r, ra);
                            una.add(rax);
                        }
                    }
                } else if (list.size() == 4) {
                    if (!list.contains("E") && !list.contains("C")) {
                        ResumenAgrupado ra = this.genraraResumenAgrupado(r, r.getQuiniela());
                        cuatro.add(ra);
                    } else if (list.contains("N") && list.contains("P") && list.contains("O")) {
                        ResumenAgrupado ra = this.genraraResumenAgrupado(r, "N P O");
                        tres.add(ra);
                        list.remove("N");
                        list.remove("P");
                        list.remove("O");
                        for (String q : list) {
                            ResumenAgrupado rax = this.genraraResumenAgrupado(r, q);
                            una.add(rax);
                        }
                    } else if (list.contains("N") && list.contains("P")) {
                        ResumenAgrupado ra2 = this.genraraResumenAgrupado(r, "N P");
                        dos.add(ra2);
                        list.stream().forEach((n) -> System.out.println("la lista de 4 elementos contine : " + n));
                        boolean isRemoved = list.remove("N");
                        boolean remove1 = list.remove("P");
                        list.stream().forEach((n) -> System.out.println("la lista de 4 elementos  - 2 contine : " + n));
                        for (String q : list) {
                            ResumenAgrupado rax = this.genraraResumenAgrupado(r, q);
                            una.add(rax);
                        }               //r.stream().forEach((n)->resumenes.add(n));

                    } else {
                        for (String q : list) {
                            ResumenAgrupado rax = this.genraraResumenAgrupado(r, q);
                            una.add(rax);
                        }
                    }
                } else if (list.size() == 3) {
                    if (!list.contains("E") && !list.contains("C") && !list.contains("F")) {
                        ResumenAgrupado ra = this.genraraResumenAgrupado(r, r.getQuiniela());
                        tres.add(ra);
                    } else if (list.contains("N") && list.contains("P")) {
                        ResumenAgrupado ra = this.genraraResumenAgrupado(r, "N P");
                        dos.add(ra);
                        list.remove("N");
                        list.remove("P");
                        ResumenAgrupado ra1 = this.genraraResumenAgrupado(r, list.get(0));
                        una.add(ra1);
                    } else {
                        for (String q : list) {
                            ResumenAgrupado rax = this.genraraResumenAgrupado(r, q);
                            una.add(rax);
                        }
                    }
                } else if (list.size() == 2) {
                    if (list.contains("N") && list.contains("P")) {
                        ResumenAgrupado ra = this.genraraResumenAgrupado(r, r.getQuiniela());
                        dos.add(ra);
                    } else {
                        for (String q : list) {
                            ResumenAgrupado rax = this.genraraResumenAgrupado(r, q);
                            una.add(rax);
                        }
                    }
                } else {
                    ResumenAgrupado rax = this.genraraResumenAgrupado(r, list.get(0));
                    una.add(rax);
                }
            }
            ArrayList<ResumenAgrupado> resumenes = new ArrayList<>();
            if (!seis.isEmpty()) {
                resumenes.addAll(seis);
            }
            if (!cinco.isEmpty()) {
                resumenes.addAll(cinco);
            }
            if (!cuatro.isEmpty()) {
                resumenes.addAll(cuatro);
            }
            if (!tres.isEmpty()) {
                resumenes.addAll(tres);
            }
            if (!dos.isEmpty()) {
                resumenes.addAll(dos);
            }
            if (!una.isEmpty()) {
                resumenes.addAll(una);
            }

            return resumenes;
        }
        ArrayList<ResumenAgrupado> resumenes = new ArrayList<>();
        return resumenes;
    }

    public ResumenAgrupado genraraResumenAgrupado(Resumen r, String q) {

        String numero = r.getNumero();
        String quinielas = q;
        double monto = r.getMonto();
        String turno = r.getTurno();

        ResumenAgrupado ra = new ResumenAgrupado(numero, 1, monto, q, turno);
        return ra;
    }

    public ArrayList<String> limpiarFormato(String quinielas) {
        ArrayList<String> list = new ArrayList<>();
        String patron = " ";
        Pattern p1 = Pattern.compile(patron);
        String[] resultado = p1.split(quinielas);

        for (String s : resultado) {
            list.add(s);
        }
        return list;
    }

}
