package primeirobimestre.prova;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculo implements Parcelable {
    private String conta;
    private String resultado;

    public Calculo(String conta, String resultado) {
        this.conta = conta;
        this.resultado = resultado;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Calculo(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Calculo> CREATOR = new Parcelable.Creator<Calculo>() {
        public Calculo createFromParcel(Parcel in) {
            return new Calculo(in);
        }

        public Calculo[] newArray(int size) {

            return new Calculo[size];
        }

    };

    public void readFromParcel(Parcel in) {
        conta = in.readString();
        resultado = in.readString();

    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(conta);
        dest.writeString(resultado);
    }
}
