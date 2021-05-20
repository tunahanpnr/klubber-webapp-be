import React, {useState} from "react";
import TextField from "@material-ui/core/TextField";
import {makeStyles} from "@material-ui/core/styles";


const useStyles = makeStyles((theme) => ({
    root: {
        display: "grid",
        // backgroundColor:"#ffeeee",
    },
    question: {
        display: 'grid',
        textAlign: "center",
        gridTemplateColumns: "1fr 1fr 1fr 1fr",
        gridColumnGap: "30px"
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
        height:"100%",
    },
}));

export default function Question(props) {
    const classes = useStyles();

    const [question, setQuestions] = useState(
        {
            question: "",
            a:"",
            b:"",
            c:"",
            d:"",
        }
    )

    const onTrigger = (event) => {
        props.callbackFromParent(question);
        event.preventDefault();
    }

    return(
        <div className={classes.root}>
            <form className={classes.form}  onSubmit={onTrigger}>
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id= {"question" + props.id}
                    label={"Question " + props.id}
                    name={"Question " + props.id}
                    autoComplete={"Question " + props.id}
                    value={question.question}
                    onChange={e => setQuestions({...question, question: e.target.value})}
                />
                <div className={classes.question}>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id={"question" + props.id + "answer1"}
                        label={"Answer 1"}
                        name={"Answer 1"}
                        autoComplete={"Answer 1"}
                        value={question.a}
                        onChange={e => setQuestions({...question, a: e.target.value})}
                    />

                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id={"question" + props.id + "answer2"}
                        label={"Answer 2"}
                        name={"Answer 2"}
                        autoComplete={"Answer 2"}
                        value={question.b}
                        onChange={e => setQuestions({...question, b: e.target.value})}
                    />

                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id={"question" + props.id + "answer2"}
                        label={"Answer 3"}
                        name={"Answer 3"}
                        autoComplete={"Answer 3"}
                        value={question.c}
                        onChange={e => setQuestions({...question, c: e.target.value})}
                    />

                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id={"question" + props.id + "answer4"}
                        label={"Answer 4"}
                        name={"Answer 4"}
                        autoComplete={"Answer 4"}
                        value={question.d}
                        onChange={e => setQuestions({...question, d: e.target.value})}
                    />
                </div>
            </form>
        </div>
    )
}