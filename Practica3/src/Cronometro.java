import java.util.Arrays;
import java.util.Objects;

public class Cronometro {
	private static final String FORMATO_TIEMPO = "0:0:0";
	public String tiempo = FORMATO_TIEMPO;
	private boolean pausa = false;

	Cronometro(String tiempo) throws CronometroException {
		this.tiempo=tiempo;
		setTiempo(tiempo);

	}

	Cronometro() {
		this.tiempo = FORMATO_TIEMPO;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) throws CronometroException {

		if (pausa) {
			throw new CronometroException("Cronometro pausado, reanudelo antes de operar");
		}

		String[] parts = this.tiempo.split(":");
		
		if (Integer.parseInt(parts[2]) < 0 || Integer.parseInt(parts[1]) < 0 || Integer.parseInt(parts[0]) < 0) {
			throw new CronometroException("Formato no valido");
		}

		if (Integer.parseInt(parts[2]) > 59 || Integer.parseInt(parts[1]) > 59) {
			throw new CronometroException("Formato no valido");
		}

		this.tiempo = tiempo;
	}

	public void incrementarCronometro() throws CronometroException {
		if (pausa) {
			throw new CronometroException("Cronometro pausado, reanudelo antes de operar");
		}
		String[] parts = this.tiempo.split(":");

		if (Integer.parseInt(parts[2]) >= 59) {
			parts[2] = "0";
			if (Integer.parseInt(parts[1]) >= 59) {
				parts[1] = "0";
				parts[0] = String.valueOf(Integer.parseInt(parts[0]) + 1);

			} else {
				parts[1] = String.valueOf(Integer.parseInt(parts[1]) + 1);

			}
		} else {
			parts[2] = String.valueOf(Integer.parseInt(parts[2]) + 1);

		}

		tiempo = parts[0] + ":" + parts[1] + ":" + parts[2];
	}

	public void reiniciarCronometro() {
		this.tiempo = FORMATO_TIEMPO;
	}

	public void pausar() {
		if (!this.pausa) {
			pausa = true;
		} else {
			pausa = false;
		}
	}

	@Override
	public String toString() {

		String[] parts = this.tiempo.split(":");

		if (Integer.parseInt(parts[2]) < 10) {
			parts[2] = "0" + parts[2];
		}

		if (Integer.parseInt(parts[1]) < 10) {
			parts[1] = "0" + parts[1];
		}

		if (Integer.parseInt(parts[0]) < 10) {
			parts[0] = "0" + parts[0];
		}
		return "Cronometro [tiempo=" + parts[0] + ":" + parts[1] + ":" + parts[2] + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(tiempo);
	}

	public int equals(Cronometro cronometro) {
		return this.tiempo.compareTo(cronometro.tiempo);
	}

}