package br.com.atrixdevelopers.rafael.example.java.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Atrix Developers
 *
 * @author Rafael de Azeredo
 */
public class Contato implements Parcelable {

    private int id;
    private String nome;
    private int idade;

    public Contato() {
        super();
    }

    protected Contato(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        idade = in.readInt();
    }

    public static final Creator<Contato> CREATOR = new Creator<Contato>() {
        @Override
        public Contato createFromParcel(Parcel in) {
            return new Contato(in);
        }

        @Override
        public Contato[] newArray(int size) {
            return new Contato[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeInt(idade);
    }
}
