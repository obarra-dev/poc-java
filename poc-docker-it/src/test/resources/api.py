from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/data', methods=['GET'])
def get_data():
    """
    Returns a simple JSON response.
    """
    data = {
        "message": "Hello from your simple API!",
        "status": "success",
        "timestamp": "2025-04-04T22:25:00Z" # Example timestamp
    }
    return jsonify(data)

if __name__ == '__main__':
    app.run(debug=True)