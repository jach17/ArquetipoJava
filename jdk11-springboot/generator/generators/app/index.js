var Generator = require('yeoman-generator');

module.exports = class extends Generator {
    
    prompting() {
        var done = this.async();
        return this.prompt([
        {
            type: 'input',
            name: 'company',
            message: 'Your company name',
            default: 'Example'
        },
        {
            type: 'input',
            name: 'name',
            message: 'Your project name',
            default: 'Catalogos'
        },{
            type: 'input',
            name: 'port',
            message: 'Your project port',
            default: '9090'
        },{
            type: 'input',
            name: 'username',
            message: 'username',
            default: 'username@axity.com'
        }]).then(((answers) => {
            this.props = answers;
            this.log('Company:', answers.company);
            this.log('Name:', answers.name);
            this.log('Port:', answers.port);
            this.log('Username:', answers.username);
            done();
        }).bind(this));
    }

    writing() {
        var done = this.async();
        this.fs.copyTpl(
            this.templatePath('**'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/`), {
                name: this.props.name,
                namelower: this.props.name.toLowerCase(),
                nameupper: this.props.name.toUpperCase(),
                namecamel: this.props.name.substring(0,1).toUpperCase() + this.props.name.substring(1).toLowerCase(),
                port: this.props.port,
                company: this.props.company,
                companylower: this.props.company.toLowerCase(),
                username: this.props.username,
            },
            {
                openDelimiter: '[',
                closeDelimiter: ']'
            }
        );
        this.fs.copyTpl(
            this.templatePath('*/**/*'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/`), {
                name: this.props.name,
                namelower: this.props.name.toLowerCase(),
                nameupper: this.props.name.toUpperCase(),
                namecamel: this.props.name.substring(0,1).toUpperCase() + this.props.name.substring(1).toLowerCase(),
                port: this.props.port,
                company: this.props.company,
                companylower: this.props.company.toLowerCase(),
                username: this.props.username,
            },
            {
                openDelimiter: '[',
                closeDelimiter: ']'
            }
        );
        this.fs.copyTpl(
            this.templatePath('.gitignore'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/.gitignore`)
          );
        done();
    }

    install() {
        var done = this.async();
        this.spawnCommand('mvn','clean package', {'cwd':`${this.props.name.toLowerCase()}-service/`})
        .on('error', function(err) {
            done(err);
        })
    }

};