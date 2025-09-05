    package model;

    import java.time.LocalDate;

    /**
     *
     * @author 20241PF.CC0014
     */
    public abstract class Pessoa {
        private String nome;
        private String cpf;
        private LocalDate dataNascimento;
        private String telefone;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String exibirDados(){
            String aux="";
            return aux;
        }
    }
