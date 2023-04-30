package br.com.service.usuario;

public class Usuario {
    
    private String primeiroNome;
    private String username;
    private String email;
    private String senha;
    private String plano;

    public Usuario(DadosCadastroUsuario dados){
        this.primeiroNome = dados.primeiroNome();
        this.username = dados.username();
        this.email = dados.email();
        this.senha = dados.senha();
        this.plano = dados.plano();
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getPlano() {
        return plano;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [primeiroNome=" + primeiroNome + ", username=" + username + ", email=" + email + ", plano=" + plano.toUpperCase() + "]";
    }

    
    
}
